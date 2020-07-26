#ifndef OUR_EXTI_H
#define OUR_EXTI_H
#include <stdint.h>

typedef void (*CALLBACK)(void);

void enablePA0interruptOnExti0WhenRising(CALLBACK pFun);
void enablePC13interruptOnExti13WhenRising(CALLBACK pFun);

#endif

