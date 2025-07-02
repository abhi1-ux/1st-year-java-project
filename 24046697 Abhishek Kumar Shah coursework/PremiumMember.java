/** Class for premium gym members with extra features. */
public class PremiumMember extends GymMember {
    protected String personalTrainer;
    protected double discountAmount;
    protected double paidAmount;
    protected String removalReason;

    /** Constructor sets up premium member with validations. */
    public PremiumMember(int id, String name, String location, String phone, String email,
                        String gender, String dob, String membershipStart, String personalTrainer,
                        double discountAmount, boolean isFullPayment) {
        super(id, name, location, phone, email, gender, dob, membershipStart);
        if (personalTrainer == null || personalTrainer.trim().isEmpty()) {
            throw new IllegalArgumentException("Personal trainer name cannot be empty.");
        }
        if (discountAmount < 0 || discountAmount > getPremiumCharge()) {
            throw new IllegalArgumentException("Discount must be between 0 and " + getPremiumCharge());
        }
        this.personalTrainer = personalTrainer.trim();
        this.discountAmount = discountAmount;
        this.paidAmount = isFullPayment ? (getPremiumCharge() - discountAmount) : 0;
        this.isFullPayment = isFullPayment;
        this.removalReason = ""; // Empty reason initially
    }

    // Getters
    public String getPersonalTrainer() { return personalTrainer; }
    public double getDiscountAmount() { return discountAmount; }
    public double getPaidAmount() { return paidAmount; }
    public boolean getIsFullPayment() { return isFullPayment; }
    public double getPremiumCharge() { return 50000; } // Fixed charge
    public String getRemovalReason() { return removalReason; }

    /** Sets valid discount amount. */
    public void setDiscountAmount(double discountAmount) {
        if (discountAmount < 0 || discountAmount > getPremiumCharge()) {
            throw new IllegalArgumentException("Discount must be between 0 and " + getPremiumCharge());
        }
        this.discountAmount = discountAmount;
    }

    // Setter for payment status
    public void setIsFullPayment(boolean isFullPayment) {
        this.isFullPayment = isFullPayment;
    }

    /** Processes payment and updates status. */
    @Override
    public void makePayment(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Payment amount cannot be negative.");
        }
        this.paidAmount += amount;
        double totalDue = getPremiumCharge() - discountAmount;
        if (paidAmount >= totalDue) {
            setIsFullPayment(true);
        }
    }

    /** Marks attendance, adds 10 points if active. */
    @Override
    public void markAttendance() {
        if (getActiveStatus()) {
            setLoyaltyPoints(getLoyaltyPoints() + 10);
        } else {
            throw new IllegalStateException("Cannot mark attendance: Membership is not activated.");
        }
    }

    /** Reverts member, clears premium features. */
    public void revertPremiumMember(String removalReason) {
        if (removalReason == null || removalReason.trim().isEmpty()) {
            throw new IllegalArgumentException("Removal reason is required for Premium Members.");
        }
        super.resetMember();
        this.personalTrainer = "";
        this.isFullPayment = false;
        this.paidAmount = 0;
        this.discountAmount = 0;
        this.removalReason = removalReason.trim();
    }

    /** Returns premium member details as string. */
    @Override
    public String toString() {
        return super.toString() + "\n" +
               "Type: Premium\n" +
               "Personal Trainer: " + personalTrainer + "\n" +
               "Discount Amount: " + discountAmount + "\n" +
               "Paid Amount: " + paidAmount + "\n" +
               "Full Payment: " + (isFullPayment ? "Yes" : "No") +
               (!removalReason.isEmpty() ? "\nRemoval Reason: " + removalReason : "");
    }
}