package com.ogomonkey.common.entity;

import java.util.Objects;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Personal extends NamedEntity {
    private static final long serialVersionUID = 1L;

    private String login;
    private String encryptedPassword;
    private String firstName;
    private String lastName;
    private PersonalType personalType;

    @Override
    public int hashCode() {
        return Objects.hash(this.login);
    }

    @Override
    public boolean equals(Object obj) {
        if (null == obj)
            return false;
        if (!(obj instanceof Personal))
            return false;

        Personal other = (Personal) obj;
        if (this.getId() != null && other.getId() != null) {
            return Objects.equals(this.getId(), other.getId());
        }

        return Objects.equals(login, other.login);
    }
}
