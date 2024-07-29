package api.payload;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class SyncResponse {

    @JsonProperty("sync_datetime")
    private String syncDatetime;

    @JsonProperty("masters")
    private Masters masters;

    public String getSyncDatetime() {
        return syncDatetime;
    }

    public void setSyncDatetime(String syncDatetime) {
        this.syncDatetime = syncDatetime;
    }

    public Masters getMasters() {
        return masters;
    }

    public void setMasters(Masters masters) {
        this.masters = masters;
    }

    public static class Masters {

        @JsonProperty("user_role")
        private List<UserRole> userRoles;

        @JsonProperty("organization")
        private List<Organization> organizations;

        @JsonProperty("countries")
        private List<Country> countries;

        public List<UserRole> getUserRoles() {
            return userRoles;
        }

        public void setUserRoles(List<UserRole> userRoles) {
            this.userRoles = userRoles;
        }

        public List<Organization> getOrganizations() {
            return organizations;
        }

        public void setOrganizations(List<Organization> organizations) {
            this.organizations = organizations;
        }

        public List<Country> getCountries() {
            return countries;
        }

        public void setCountries(List<Country> countries) {
            this.countries = countries;
        }
    }

    public static class UserRole {

        @JsonProperty("id")
        private int id;

        @JsonProperty("name")
        private String name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class Organization {

        @JsonProperty("id")
        private int id;

        @JsonProperty("name")
        private String name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class Country {

        @JsonProperty("id")
        private int id;

        @JsonProperty("name")
        private String name;

        @JsonProperty("abbreviation")
        private String abbreviation;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAbbreviation() {
            return abbreviation;
        }

        public void setAbbreviation(String abbreviation) {
            this.abbreviation = abbreviation;
        }
    }
}
