package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            //회원 등록
            Member member = new Member();
            member.setId(2L);
            member.setName("HelloB");

            em.persist(member);

            // 회원 단 건 조회
            Member findMember = em.find(Member.class, 1L);
            System.out.println("findMember.id() = " + findMember.getId());
            System.out.println("findMember.name() = " + findMember.getName());

            // 회원 삭제
            Member findMember1 = em.find(Member.class, 1L);
            em.remove(findMember1);

            // 회원 수정
            Member findMember2 = em.find(Member.class, 2L);
            findMember2.setName("HelloJPA");

            tx.commit();

        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
