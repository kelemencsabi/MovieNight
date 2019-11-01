package org.fasttrackit.MovieNight.transfer.actor;

public class GetActorsRequest {
    public String partialFirstName;
    public String partialLastName;
    public Integer age;

    public String getPartialFirstName() {
        return partialFirstName;
    }

    public void setPartialFirstName(String partialFirstName) {
        partialFirstName = partialFirstName;
    }

    public String getPartialLastName() {
        return partialLastName;
    }

    public void setPartialLastName(String partialLastName) {
        partialLastName = partialLastName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "GetActorsRequest{" +
                "PartialFirstName='" + partialFirstName + '\'' +
                ", PartialLastName='" + partialLastName + '\'' +
                ", age=" + age +
                '}';
    }
}
