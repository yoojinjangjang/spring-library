package ssafy.library;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Hello {
    private  String example;

    public static void main(String[] args) {
        Hello hello = new Hello();
        hello.setExample("example");
        System.out.println(hello.getExample());
    }
}

