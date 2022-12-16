package cz.osu.itemrecordsbe.repositories;

import cz.osu.itemrecordsbe.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

}
