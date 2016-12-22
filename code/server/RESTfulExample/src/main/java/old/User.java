package old;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author HiepHolmes
 */

public class User {

    private String name;
    private String password;
    private String phone;
    private String address;
    private boolean can_view;
    private boolean can_update;
    private boolean can_delete;

    
    
    public User() {
	
	}

	public User(String name, String password, String phone, String address, boolean can_view, boolean can_update, boolean can_delete) {
        this.name = name;
        this.password = password;
        this.phone = phone;
        this.address = address;
        this.can_view = can_view;
        this.can_update = can_update;
        this.can_delete = can_delete;
    }

	
	
    @Override
	public String toString() {
		return "User [name=" + name + ", password=" + password + ", phone=" + phone + ", address=" + address
				+ ", can_view=" + can_view + ", can_update=" + can_update + ", can_delete=" + can_delete + "]";
	}

	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isCan_view() {
        return can_view;
    }

    public void setCan_view(boolean can_view) {
        this.can_view = can_view;
    }

    public boolean isCan_update() {
        return can_update;
    }

    public void setCan_update(boolean can_update) {
        this.can_update = can_update;
    }

    public boolean isCan_delete() {
        return can_delete;
    }

    public void setCan_delete(boolean can_delete) {
        this.can_delete = can_delete;
    }
    
    

    
    
}
