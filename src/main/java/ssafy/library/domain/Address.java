package ssafy.library.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import lombok.Getter;

@Getter
@Embeddable
public class Address {
    @Column(nullable = false, length = 5)
    private String zipcode;
    @Column(nullable = false)
    private String mainAddress;
    @Column(nullable = false)
    private String subAddress;

    protected Address(){}

    public Address(String zipcode, String mainAddress, String subAddress) {
        this.zipcode = zipcode;
        this.mainAddress = mainAddress;
        this.subAddress = subAddress;
    }
}
