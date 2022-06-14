package commands;

import lombok.Getter;

import java.io.Serializable;

public class DataForArray implements Serializable {
    @Getter
    private String[] args;
    @Getter
    private final String name;
    @Getter
    private final String password;
    public DataForArray(String[] args) {
        this.args = args;
        name = null;
        password = null;
    }

    public DataForArray(String[] args,String name, String password) {
        this.args = args;
        this.name = name;
        this.password = password;
    }
}
