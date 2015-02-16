package se.rapid.changepassword;

import java.io.Serializable;

public class ChangePasswordForm implements Serializable {

    private static final long serialVersionUID = 4979863811136205622L;

    private String original;
    private String password;
    private String confirm;

    public String getOriginal() {
        return original;
    }

    public void setOriginal(String original) {
        this.original = original;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirm() {
        return confirm;
    }

    public void setConfirm(String confirm) {
        this.confirm = confirm;
    }
}       