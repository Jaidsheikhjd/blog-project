package com.blog.blog.reositories;

import java.util.List;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.blog.blog.entities.Category;
import com.blog.blog.entities.Post;
import com.blog.blog.entities.User;

public interface PostRepo extends JpaRepository<Post,Integer> {
    
      List<Post> findByUser(User user);
      List<Post> findByCategory(Category category);
      List<Post> findByTitleContaining(String title);

//       String sql = "SELECT * FROM YourEntity LIMIT ? OFFSET ?";
// List<YourEntity> entities = jdbcTemplate.query(sql,  new Object[]{limit, offset}, (rs,rowNum)->{
//     return new YourEntity(rs.getString(col1),rs.getString(col2)....);
// });
//      @Query("select * from Post p where Limit :limit OFFSET :offset ");
// List<Post> findallpost(@Param("offset") int offset,@Param("limit") int limit);


}
