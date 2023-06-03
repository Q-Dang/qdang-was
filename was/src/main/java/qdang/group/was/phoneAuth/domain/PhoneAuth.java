package qdang.group.was.phoneAuth.domain;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;

@Getter
@Entity
public class PhoneAuth {

    @Id
    private Long id;
}
