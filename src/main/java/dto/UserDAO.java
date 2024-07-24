package dto;


import com.fitBuddyGuy.fitBuddyApp.model.User;
import jakarta.validation.Valid;
import lombok.Data;


@Data
public class UserDAO {

   @Valid
   private String username;

   @Valid
   private String activityLevel;



   private int age;


   private int weight;

   @Valid
   private String user_goal;


   public UserDAO(User userAccess, String username, String activityLevel, int age, int weight, String user_goal) {

       userAccess.setusername(username);
       userAccess.setUser_goal(user_goal);
       userAccess.setWeight(weight);
       userAccess.setActivityLevel(activityLevel);
       userAccess.setAge(age);

    }

    public UserDAO() {

    }


   public String getUsername() {
      return username;
   }

   public void setUsername(String username) {
      this.username = username;
   }

   public String getActivityLevel() {
      return activityLevel;
   }

   public void setActivityLevel(String activityLevel) {
      this.activityLevel = activityLevel;
   }

   public int getAge() {
      return age;
   }

   public void setAge(int age) {
      this.age = age;
   }

   public int getWeight() {
      return weight;
   }

   public void setWeight(int weight) {
      this.weight = weight;
   }

   public String getUser_goal() {
      return user_goal;
   }

   public void setUser_goal(String user_goal) {
      this.user_goal = user_goal;
   }
}
