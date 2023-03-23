#include <stdio.h>
#include <sys/types.h>

int main(void)
{
	pid_t pid = fork();

	if(pid > 0) pid = fork();
	if(pid > 0) pid = fork();

	printf("Process started from program. Every process prints this message. Current PID is %d My Parent is %d\n", getpid(), getppid());

	if(pid > 0)
		printf("Message printed by parent");

	if(pid == 0) {
		printf("Message printed by child");
		exit(0);
	}
	return 0;
}