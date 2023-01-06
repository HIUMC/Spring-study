package springstudy.basic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springstudy.basic.domain.Member;

import java.util.Optional;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {

    Optional<Member> findByName(String name);
}
