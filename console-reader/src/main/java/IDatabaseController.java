public interface IDatabaseController {
     void initConnection();


    void addPerson(int id, String name, int age);

    void readPeople(String readBy, String value);

    void updatePeople(String updateBy, String value);
}
