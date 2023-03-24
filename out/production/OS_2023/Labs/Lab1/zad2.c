#include <stdio.h>
#include <stdlib.h>
#include <sys/wait.h>
#include <sys/types.h>

int main(int argc, char *argv[]) {

    pid_t pid;
    int num;
    if ((pid = fork()) == 0) { /* child */
        sleep(3);
        printf("Proces dete\n");
        for(;;) {
            printf("Vnesete broj: \n");
            scanf("%d", &num);
            printf("Vnesovte %d. ", num);
            if (num == 0)  {
                printf("Se budam\n");
                exit(0);
            }
        }
    }
    else {
        if (pid > 0) {
            printf("Proces tatko\n");
            wait(NULL);
            printf("Povtorno proces tatko\n");
        }
    }
}
