package it.lei.day1.entity;

public class Permission {
    private Integer permissionId;

    private String permissionName;

    private String permissionCode;

    private String permissionInfo;

    public Integer getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Integer permissionId) {
        this.permissionId = permissionId;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName == null ? null : permissionName.trim();
    }

    public String getPermissionCode() {
        return permissionCode;
    }

    public void setPermissionCode(String permissionCode) {
        this.permissionCode = permissionCode == null ? null : permissionCode.trim();
    }

    public String getPermissionInfo() {
        return permissionInfo;
    }

    public void setPermissionInfo(String permissionInfo) {
        this.permissionInfo = permissionInfo == null ? null : permissionInfo.trim();
    }
}