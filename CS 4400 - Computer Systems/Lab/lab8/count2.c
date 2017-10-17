#include "csapp.h"

static int got = 0;

void handle(int sig){
  sio_putl(got);
  sio_puts("\n");
  _exit(1);
}

int main() {
  pid_t pid;

  pid = Fork();
  if (pid == 0) {
    char buffer[256];
    int n = 0;

    Signal(SIGINT, handle);
  
    while (1) {
      n = Read(0, buffer, sizeof(buffer));
      if (!n)
        break;
      got += n;
    }

    printf("got %d\n", got);
  } else {
    Waitpid(pid, NULL, 0);
  }

  return 0;
}