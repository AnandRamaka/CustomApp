public class Question {
    private String question, answer;
    public Question(String q, String a){
        question = q;
        answer = a;
    }
    String getQuestion(){
        return question;
    }
    String getAnswer(){
        return answer;
    }
}
