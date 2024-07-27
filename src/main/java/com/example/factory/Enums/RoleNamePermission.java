package com.example.factory.Enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum RoleNamePermission {

    REGISTER_CUSTOMER("Manage Users","Gives the user permission to view and manage all members and guests in a Workspace." +
            " This includes adding and removing users, changing roles, and managing invites.",
            Arrays.asList(RoleName.Staff,RoleName.Director,RoleName.Foreman)),


    GET_CUSTOMER_INFORMATION("GIT","Allows the user to see and open the Github/Bitbucket/Gitlab modal on tasks and use all the features within it.",
            Arrays.asList(RoleName.Staff,RoleName.Director,RoleName.Foreman)),


    EDIT_CUSTOMER("Edit Statuses","Gives the user the permission to create, edit, and delete statuses. " +
            "If you have Edit Statuses toggled on, but Delete Items off, you will not be able to delete statuses.",
            Arrays.asList(RoleName.Staff,RoleName.Director,RoleName.Foreman)),


    ARCHIVE_CUSTOMER("Manage Tags","Gives the user the permission to create, edit, and delete tags. " +
            "If you have Manage Tags toggled on, but Delete Items off, you will not be able to delete tags.",
            Arrays.asList(RoleName.Staff,RoleName.Director,RoleName.Foreman)),


    VIEW_SELF_CUSTOMER("Send Email (Email ClickApp)","Gives the user the permission to send email through the Email ClickApp.",
            Arrays.asList(RoleName.Staff,RoleName.Director,RoleName.Foreman));





    private String name;
    private  String description;

    private List<RoleName> workspaceRoleNames;

}
