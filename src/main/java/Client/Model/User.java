package Client.Model;

import Client.Controller.Controller;
import Client.Controller.ManageData;

import java.time.LocalTime;
import java.util.ArrayList;

public class User {
    String username;
    String password;
    String nickname;
    String email;
    String slogan;
    String answer;
    int questionNumber;
    int highScore;

    private String imageUrl = Game.class.getResource("/Images/Avatars/1.png").toString();
    String id;

    ArrayList<String> friendsId = new ArrayList<>();
    ArrayList<String> pendingFriendRequestsId = new ArrayList<>();

    public void updateUser(User user){
        if (user != null && user.getId().equals(this.getId())) {
            this.friendsId = user.getFriendsId();
            this.pendingFriendRequestsId = user.getPendingFriendRequestsId();
            return;
        } else if (user == null || !user.getId().equals(this.getId())) return;
        this.setUsername(user.getUsername());
        this.setPassword(user.getPassword());
        this.setNickname(user.getNickname());
        this.setEmail(user.getEmail());
        this.setSlogan(user.getSlogan());
        this.setHighScore(user.getHighScore());
    }

    public boolean addToFriendRequests(String id){
        if (!(pendingFriendRequestsId.contains(id)) && !(friendsId.contains(id))) {
            pendingFriendRequestsId.add(id);
            return true;
        }
        return false;
    }

    public void removeFromFriendRequests(String id){
        pendingFriendRequestsId.remove(id);
    }

    public boolean addToFriends(String id){
        if (pendingFriendRequestsId.contains(id)) {
            pendingFriendRequestsId.remove(id);
            friendsId.add(id);
            Controller.findUserById(id).addToFriends(this.getId());
            return true;
        }
        return false;
    }

    public ArrayList<String> getFriendsId() {
        return friendsId;
    }

    public ArrayList<String> getPendingFriendRequestsId() {
        return pendingFriendRequestsId;
    }

    public String getId() {
        return id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public User() {
    }

    public User(String username, String password, String nickname, String email, String slogan, String answer, int questionNumber) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.email = email;
        this.slogan = slogan;
        this.answer = answer;
        this.questionNumber = questionNumber;
        this.highScore = 0;

        double idBuffer = Math.pow(LocalTime.now().getNano(),2) % 999999967;
        this.id = String.valueOf((int) idBuffer);
    }

    public static void createUser(String username, String password, String nickname, String email, String slogan, String answer, int questionNumber) {
        User user = new User(username, ManageData.encrypt(password), nickname, email, slogan, answer, questionNumber);
        Controller.addUser(user);
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public String getEmail() {
        return email;
    }

    public String getSlogan() {
        return slogan;
    }

    public String getAnswer() {
        return answer;
    }

    public int getQuestionNumber() {
        return questionNumber;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSlogan(String slogan) {
        this.slogan = slogan;
    }

    public int getHighScore() {
        return highScore;
    }

    public void setHighScore(int highScore) {
        this.highScore = highScore;
    }

    public void addHighScore(int amount) {
        this.highScore += amount;
    }

}