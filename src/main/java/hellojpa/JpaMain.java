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


            //1차 캐시
//            example1(em);

            //영속 엔티티의 동일성 보장
//            example2(em);

            //트랜잭션을 지원하는 쓰기 지연
//            example3(em);

            //변경 감지(Dirty Checking)
            Member member = em.find(Member.class, 150L);
            member.setName("ZZZZZZ");

            System.out.println("======================");
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }

    private static void example3(EntityManager em) {
        Member member1 = new Member(150L, "A");
        Member member2 = new Member(160L, "B");

        em.persist(member1);
        em.persist(member2);

        System.out.println("======================");
    }

    private static void example2(EntityManager em) {
        Member findMember1 = em.find(Member.class, 101L);
        Member findMember2 = em.find(Member.class, 101L);

        System.out.println("findMember1 == findMember2 : " + (findMember1 == findMember2));
    }

    private static void example1(EntityManager em) {
        Member member = new Member();
        member.setId(101L);
        member.setName("HelloJPA");

        em.persist(member);

        Member findMember = em.find(Member.class, 101L);
        System.out.println("findMember.getId() = " + findMember.getId());
        System.out.println("findMember.getName() = " + findMember.getName());
    }
}
