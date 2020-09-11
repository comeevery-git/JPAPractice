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
			//생성
//			Member member = new Member();
//			member.setId(1L);
//			member.setName("helloA");
//			em.persist(member);
			
			//수정
//			Member findMember = em.find(Member.class, 1L);
//			findMember.setName("TEST");
			
			//테이블 기준이 아닌 Member 객체를 기준으로 가져옴 (select m)
			List<Member> result = em.createQuery("select m from Member as m", Member.class).getResultList();
			
			for (Member member : result) {
				System.out.println("member.name = " +member.getName());
			}
			
			tx.commit();	
		} catch(Exception e) {
			System.out.println("error : "+e);
		} finally {
			em.close();
		}
		
		emf.close();
	}

}
