/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package enums;

/**
 *
 * @author gazda
 */
public enum RehabilitationPlanStatus {
    
    // Initial phase
    PENDING_APPROVAL("Pending approval"),
    
    // Active phase
    IN_PROGRESS("In progress"),
    TEMPORARILY_HALTED("Temporarily halted"),
    WAITING_FOR_NEXT_CHECKUP("Waiting for next checkup"),
    
    // Progress phase
    PARTIAL_RECOVERY("Partial recovery"),
    IMPROVEMENT("Improvement"),
    NO_PROGRESS("No progress"),
    
    // Completion phase
    SUCCESSFULLY_COMPLETED("Successfully completed"),
    REHABILITATION_TERMINATED("Rehabilitation terminated"),
    REJECTED("Rejected"),
    
    // Post-completion monitoring
    FOLLOW_UP("Follow-up");

    private final String description;

    // Constructor for status description
    RehabilitationPlanStatus(String description) {
        this.description = description;
    }

    // Getter to retrieve the status description
    public String getDescription() {
        return description;
    }
    
}
