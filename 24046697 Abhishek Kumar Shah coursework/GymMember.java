/** Abstract class for gym members, providing core attributes and methods. */
public abstract class GymMember {
    // Member details
    protected int id;
    protected String name;
    protected String location;
    protected String phone;
    protected String email;
    protected String gender;
    protected String dob;
    protected String membershipStart;
    protected boolean activeStatus;
    protected int loyaltyPoints;
    protected boolean isFullPayment;

    /** Constructor initializes member with provided details. */
    public GymMember(int id, String name, String location, String phone, String email,
                    String gender, String dob, String membershipStart) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.phone = phone;
        this.email = email;
        this.gender = gender;
        this.dob = dob;
        this.membershipStart = membershipStart;
        this.activeStatus = false; // Default inactive
        this.loyaltyPoints = 0; // Start with zero points
        this.isFullPayment = false; // Payment not completed
    }

    // Getters
    public int getId() { return id; }
    public String getName() { return name; }
    public String getLocation() { return location; }
    public String getPhone() { return phone; }
    public String getEmail() { return email; }
    public String getGender() { return gender; }
    public String getDob() { return dob; }
    public String getMembershipStart() { return membershipStart; }
    public boolean getActiveStatus() { return activeStatus; }
    public int getLoyaltyPoints() { return loyaltyPoints; }
    public boolean getIsFullPayment() { return isFullPayment; }

    // Setter for payment status
    public void setIsFullPayment(boolean isFullPayment) {
        this.isFullPayment = isFullPayment;
    }

    /** Resets member status and clears points. */
    public void resetMember() {
        this.loyaltyPoints = 0;
        this.activeStatus = false;
        this.isFullPayment = false;
    }

    // Abstract methods for subclasses
    public abstract void makePayment(double amount);
    public abstract void markAttendance();

    /** Activates membership. */
    public void activateMembership() { 
        this.activeStatus = true; 
    }

    /** Deactivates membership if active, else shows message. */
    public void deactivateMembership() {    
        if (this.activeStatus) {    
            this.activeStatus = false;
        } else {    
            System.out.println("Your membership status is already deactivated.");
        }
    }

    // Sets loyalty points for subclasses
    protected void setLoyaltyPoints(int points) {
        this.loyaltyPoints = points;
    }
}