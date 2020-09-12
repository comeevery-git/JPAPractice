package com.example.study;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.springframework.boot.SpringApplication;

public class JpaPracticeApplication {

	public static void main(String[] args) {
		SpringApplication.run(JpaPracticeApplication.class, args);

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		try {
			//생성, 비영속 상태
//			Member member = new Member();
//			member.setId(5L);
//			member.setName("hello5");
			//영속 상태(영속성 컨텍스트에 의해 관리되는 상태)
//			em.persist(member);
			
//			Member findMember = em.find(Member.class, 5L);
			//영속 상태인 member pk 값 조회로 select 문 실행되지 않음
//			System.out.println("findMember.id = "+findMember.getId());
			
			//1차 캐시에 없을 때 DB에서 조회
//			Member findMember = em.find(Member.class, 5L);
			//DB에서 조회 후 1차 캐시에 저장했으므로 select문 미실행
//			Member findMember2 = em.find(Member.class, 5L);

//			Member member1 = new Member(6L, "A");
//			Member member2 = new Member(7L, "B");
//			em.persist(member1);
//			em.persist(member2);
			
//			System.out.println("------ insert문은 언제 실행되는가? ------");
			//트랜잭션 커밋 시 쿼리실행
			//hibernate.jdbc.batch_size 옵션 설정으로 모을 갯수 지정가능
//			tx.commit();

			//수정은 update문 작성하지 않고 아래처럼 작성하는게 옳음
//			Member member = em.find(Member.class, 1L);
//			member.setName("TEST");
			/* 수정 데이터를 확인하려는 아래 코드는 불필요
			if(member.getName().equals("TEST")) {
				em.update(member);
			}
			*/
			
			Member member = new Member(8L,"hello8");
			em.persist(member);

			//flush 직접호출, 1차 캐시 데이터가 삭제되지는 않음
			//쓰기 지연 SQL 저장소에 있는 쿼리들(변경감지) 실행되는 것 뿐.. 반영
			em.flush();
			System.out.println("===========");
			//DB Transaction commit 이전 직전호출로 이미 실행됨
			tx.commit();
			
			
			
			//테이블 기준이 아닌 Member 객체를 기준으로 가져옴 (select m)
//			List<Member> result = em.createQuery("select m from Member as m", Member.class).getResultList();
//			
//			for (Member member : result) {
//				System.out.println("member.name = " +member.getName());
//			}
			
			tx.commit();	
		} catch(Exception e) {
			System.out.println("error : "+e);
		} finally {
			em.close();
		}
		
		emf.close();
	}

}
