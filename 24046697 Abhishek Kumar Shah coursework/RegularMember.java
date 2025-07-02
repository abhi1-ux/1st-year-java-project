/** Class for regular gym members with plan-based pricing. */
public class RegularMember extends GymMember {
    protected String plan;
    protected double price;
    protected String referral;
    protected boolean isEligibleForUpgrade;
    protected String removalReason;
    protected double paidAmount;

    /** Constructor initializes regular member with validations. */
    public RegularMember(int id, String name, String location, String phone, String email,
                        String gender, String dob, String membershipStart, String referral,
                        String plan, double price, boolean isFullPayment) {
        super(id, name, location, phone, email, gender, dob, membershipStart);
        if (referral == null || referral.trim().isEmpty()) {
            throw new IllegalArgumentException("Referral cannot be empty.");
        }
        if (plan == null || !(plan.equalsIgnoreCase("basic") || plan.equalsIgnoreCase("standard") || plan.equalsIgnoreCase("deluxe"))) {
            throw new IllegalArgumentException("Plan must be basic, standard, or deluxe.");
        }
        if (price <= 0) {
            throw new IllegalArgumentException("Price must be positive.");
        }
        this.plan = plan.toLowerCase();
        this.price = price;
        this.referral = referral.trim();
        this.isFullPayment = isFullPayment;
        this.isEligibleForUpgrade = true; // Eligible by default
        this.removalReason = ""; // Empty reason initially
        this.paidAmount = isFullPayment ? price : 0; // Set paid amount
    }

    // Getters
    public String getPlan() { return plan; }
    public double getPrice() { return price; }
    public String getReferral() { return referral; }
    public boolean getIsFullPayment() { return isFullPayment; }
    public boolean getIsEligibleForUpgrade() { return isEligibleForUpgrade; }
    public String getRemovalReason() { return removalReason; }
    public double getPaidAmount() { return paidAmount; }

    // Setter for payment status
    public void setIsFullPayment(boolean isFullPayment) {
        this.isFullPayment = isFullPayment;
    }

    /** Processes payment, checks for overpayment. */
    @Override
    public void makePayment(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Payment amount cannot be negative.");
        }
        if (paidAmount + amount > price) {
            throw new IllegalArgumentException("Payment exceeds remaining balance of " + (price - paidAmount));
        }
        this.paidAmount += amount;
        if (paidAmount >= price) {
            setIsFullPayment(true);
        }
    }

    /** Marks attendance, adds 5 points if active. */
    @Override
    public void markAttendance() {
        if (getActiveStatus()) {
            setLoyaltyPoints(getLoyaltyPoints() + 5);
        } else {
            throw new IllegalStateException("Cannot mark attendance: Membership is not activated.");
        }
    }

    /** Upgrades plan if eligible and points are sufficient. */
    public void upgradePlan(String newPlan) {
        if (newPlan == null || !(newPlan.equalsIgnoreCase("basic") || newPlan.equalsIgnoreCase("standard") || newPlan.equalsIgnoreCase("deluxe"))) {
            throw new IllegalArgumentException("Invalid plan: " + newPlan);
        }
        if (!isEligibleForUpgrade) {
            throw new IllegalStateException("Member is not eligible for upgrade.");
        }
        if (getLoyaltyPoints() <= 150) {
            throw new IllegalStateException("Cannot upgrade: Member must have at least 30 attendances (150 loyalty points). Current points: " + getLoyaltyPoints());
        }
        this.plan = newPlan.toLowerCase();
        switch (this.plan) {
            case "basic": this.price = 6500; break;
            case "standard": this.price = 12500; break;
            case "deluxe": this.price = 18500; break;
        }
        this.paidAmount = 0; // Reset payment
        setIsFullPayment(false);
    }

    /** Reverts member to basic plan, clears eligibility. */
    public void revertRegularMember(String removalReason) {
        if (removalReason == null || removalReason.trim().isEmpty()) {
            throw new IllegalArgumentException("Removal reason is required for Regular Members.");
        }
        super.resetMember();
        this.isEligibleForUpgrade = false;
        this.plan = "basic";
        this.price = 6500;
        this.paidAmount = 0;
        this.removalReason = removalReason.trim();
    }

    /** Returns regular member details as string. */
    @Override
    public String toString() {
        return super.toString() + "\n" +
               "Type: Regular\n" +
               "Plan: " + plan + "\n" +
               "Price: " + price + "\n" +
               "Referral: " + referral + "\n" +
               "Paid Amount: " + paidAmount + "\n" +
               "Full Payment: " + (isFullPayment ? "Yes" : "No") + "\n" +
               "Eligible for Upgrade: " + (isEligibleForUpgrade ? "Yes" : "No") +
               (!removalReason.isEmpty() ? "\nRemoval Reason: " + removalReason : "");
    }
}