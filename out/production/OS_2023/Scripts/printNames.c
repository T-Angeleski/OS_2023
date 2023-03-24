#include <stdio.h>
#include <sys/types.h>

int main()
{
    int i;
    for (i = 0; i < 2; i++)
    {
        pid_t pid = fork();

        if (pid == 0)
        {
            fork();
            printf("Veronika\n");
        }
        else
        {
            printf("Hristina\n");
        }
    }

    printf("Eva\n");
    return 0;
}
