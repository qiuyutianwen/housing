package com.cmpe202.demo.idclass;

import java.io.Serializable;
import java.util.Objects;

public class ApplicationId implements Serializable {
    private String applicant;
    private  String listingId;

    public ApplicationId() {
    }

    public ApplicationId(String applicant, String listingId) {
        this.applicant = applicant;
        this.listingId = listingId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(applicant,listingId);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        ApplicationId id = (ApplicationId) obj;
        return id.applicant.equals(this.applicant) &&
                id.listingId.equals(this.listingId);
    }
}
