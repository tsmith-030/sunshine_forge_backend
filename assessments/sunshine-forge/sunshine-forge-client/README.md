# Sunshine Forge Front End

## Background

You have been hired as a developer at Sunshine Forge - a young startup that intends to compete with Cloud Foundry.

The API has been created for you, and you are now tasked with creating a front end using React.

For this assignment, you don't yet need to use Router or Redux (we will learn more about those later). 

Instead you will be making a "vanilla" react app using state and props to show and hide components as necessary.

All logic should take place in a RootComponent, and other components should communicate by firing events up to the root.

You will not need any form of caching: just reload the entire state tree after each action the user takes.


## Space Epic

1. As a Cloud Native Developer, I want to create a new Space and specificy the name, disk, and memory quota so that I can use this space to deploy my applications. 
1. As a Cloud Native Developer, I would like a list of all Spaces and the percentage of memory consumed.
1. As a Cloud Native Developer, I would like to see details of a selected Space.
1. As a Cloud Native Developer, I would like to change the name, disk and memory quota of a space (I may change my mind once I start editing).
    - An attempt to decrease the memory or disk quota for a Space below what is required for existing Applications, displays a friendly error.
1. As a Cloud Native Developer, given that I am viewing the details of a Space, I would like to see a list of apps contained under that space.
1. As a Cloud Native Developer, I would like to delete Spaces that are no longer used (sometimes I make mistakes and click delete when I didn't mean to)

## Space Mockups

![Add space](../img/add_space.png)
![Create space](../img/space_create.png)
![Space Details](../img/space_details.png)
![Space Edit](../img/space_edit.png)
![Space Delete](../img/space_delete.png)

## App Epic

1. As a Cloud Native Developer, I want to create a new Application and specificy the name, disk, and memory allocation so that I can deploy my application.
    - An attempt to create an Application which exceeds the Space memory or disk quota, displays friendly error.
1. As a Cloud Native Developer, I would like a list of all Application within a given Space.
1. As a Cloud Native Developer, I would like to be taken to a screen where I can see details about the app.
1. As a Cloud Native Developer, I would like to see details of a selected Application.
1. As a Cloud Native Developer, I would like to change the name, disk and memory allocation of an Application (I may change my mind once I start editing).
- An attempt to edit an Application which results in exceeding the Space memory or disk quota, displays friendly error.
1. As a Cloud Native Developer, I would like to delete Applications that are no longer used (sometimes I make mistakes and click delete when I didn't mean to)

## App Mockups

![Add App](../img/add_app.png)
![Create App](../img/app_create.png)
![App List](../img/app_list.png)
![App Details](../img/app_details.png)
![App Edit](../img/app_edit.png)
![App Delete](../img/app_delete.png)

## Business Logic and Error Handling Screenshots

![Create App Disk Quota](../img/create_app_disk_quota.png)
![Create App Memory Quota](../img/create_app_memory_quota.png)
![Edit App Disk Quota](../img/edit_app_disk_quota.png)
![Edit App Memory Quota](../img/edit_app_memory_quota.png)
![Edit Space Disk Quota](../img/edit_space_disk_quota.png)
![Edit Space Memory Quota](../img/edit_space_memory_quota.png)
