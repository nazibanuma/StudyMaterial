#include <stdio.h>
#include <stdlib.h>
#include <string.h>

struct Prisoner
{
    int id;
    char name[50];
    char crime[50];
    int sentence;
};

struct Prisoner data[100];
int t = 0; // total
const char *file = "prisoners.txt";

void load();
void save();
void login();
void menu();
void add();
void showAll();
void find();
void update();
void removePrisoner();

int main()
{
#include <stdio.h>

    printf("  _____________________  \n");
    printf(" |                     | \n");
    printf(" |    CENTRAL  JAIL    | \n");
    printf(" |_____________________| \n");

    load();
    login();
    return 0;
}

void load()
{
    FILE *fp = fopen(file, "r");
    if (fp == NULL)
        return;
    while (fscanf(fp, "%d    %49s    %49s    %d",
                  &data[t].id,
                  data[t].name,
                  data[t].crime,
                  &data[t].sentence) == 4)
    {
        t++;
    }
    fclose(fp);
}

void save()
{
    FILE *fp = fopen(file, "w");
    if (fp == NULL)
    {
        printf("Couldn't write to file.\n");
        return;
    }
    for (int i = 0; i < t; i++)
    {
        fprintf(fp, "%d    %s    %s    %d\n",
                data[i].id,
                data[i].name,
                data[i].crime,
                data[i].sentence);
    }
    fclose(fp);
}

void login()
{
    char uname[20], pwd[20];
    printf("===== Welcome to the Prison Management System =====\n\n");
    printf("Username: ");
    scanf("%s", uname);
    printf("Password: ");
    scanf("%s", pwd);

    if (strcmp(uname, "Prison_Admin") == 0 && strcmp(pwd, "2025") == 0)
    {
        printf("\nLogin success!\n\n");
        menu();
    }
    else
    {
        printf("\nWrong username or password.\n");
        exit(0);
    }
}

void menu()
{
    int opt;
    do
    {
        printf("\n===== MENU =====\n");
        printf("1. Add Prisoner\n");
        printf("2. View All Prisoners\n");
        printf("3. Search Prisoner\n");
        printf("4. Edit Prisoner Info\n");
        printf("5. Delete Prisoner\n");
        printf("0. Exit\n");
        printf("Choose an option: ");
        scanf("%d", &opt);

        switch (opt)
        {
        case 1:
            add();
            break;
        case 2:
            showAll();
            break;
        case 3:
            find();
            break;
        case 4:
            update();
            break;
        case 5:
            removePrisoner();
            break;
        case 0:
            save();
            printf("\nInformation saved successfully\n");
            break;
        default:
            printf("\nInvalid option!\n");
        }
    } while (opt != 0);
}

void add()
{
    printf("\n----- Add New Prisoner -----\n");
    printf("ID : ");
    scanf("%d", &data[t].id);
    fflush(stdin);
    printf("Name : ");
    scanf("%s", data[t].name);
    fflush(stdin);
    printf("Crime : ");
    scanf("%s", data[t].crime);
    fflush(stdin);
    printf("Sentence (years): ");
    scanf("%d", &data[t].sentence);
    fflush(stdin);
    t++;
    printf("\nPrisoner added.\n");
    printf("------------------------------\n");
}

void showAll()
{
    if (t == 0)
    {
        printf("\nNo prisoner records available.\n\n");
        return;
    }
    printf("\n----- Prisoner Records -----\n");
    for (int i = 0; i < t; i++)
    {
        printf("\nRecord #%d\n", i + 1);
        printf("Name     : %s\n", data[i].name);
        printf("ID       : %d\n", data[i].id);
        printf("Crime    : %s\n", data[i].crime);
        printf("Sentence : %d years\n", data[i].sentence);
    }
    printf("\n----------------------------\n");
}
void find()
{
    char key[50];
    int found = 0;
    fflush(stdin);

    printf("\n------ Search Results ------\n");
    printf("\nEnter name to search :");
    gets(key);

    for (int i = 0; i < t; i++)
    {
        if (strstr(data[i].name, key) != NULL)
        {
            printf("\nPrisoner Found:\n");
            printf("ID       : %d\n", data[i].id);
            printf("Name     : %s\n", data[i].name);
            printf("Crime    : %s\n", data[i].crime);
            printf("Sentence : %d years\n", data[i].sentence);
            found = 1;
        }
    }
    printf("\n----------------------------\n");

    if (!found)
    {
        printf("No prisoner found with that name.\n");
    }
}

void update()
{
    int editId;
    printf("\n----- Edit Prioners -----\n");
    printf("\nEnter ID to edit: ");
    scanf("%d", &editId);
    for (int i = 0; i < t; i++)
    {
        if (data[i].id == editId)
        {
            printf("New name : ");
            scanf("%s", data[i].name);
            fflush(stdin);
            printf("New crime : ");
            scanf("%s", data[i].crime);
            fflush(stdin);
            printf("New sentence (years): ");
            scanf("%d", &data[i].sentence);
            fflush(stdin);
            printf("\nUpdated successfully.\n");
            printf("\n--------------------------\n");
            return;
        }
    }
    printf("\nPrisoner not found.\n\n");
}

void removePrisoner()
{
    int delId;
    printf("\n----- Delete Prisoners -----\n");
    printf("\nEnter ID to delete: ");
    scanf("%d", &delId);
    for (int i = 0; i < t; i++)
    {
        if (data[i].id == delId)
        {
            for (int j = i; j < t - 1; j++)
            {
                data[j] = data[j + 1];
            }
            t--;
            printf("\nDeleted successfully.\n");
            printf("\n-----------------------------\n");
            return;
        }
    }
    printf("\nNo such ID found.\n\n");
}
