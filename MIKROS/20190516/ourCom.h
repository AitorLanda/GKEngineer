#ifndef OUR_COM_H
#define OUR_COM_H

#include <stdint.h>
#include <stm32f407xx.h>
#include "ourRccGpio.h"
#include "sysClockAndTick.h"

typedef enum e_Com {COM1,COM2} COM;

uint32_t initCom(COM com, uint32_t baudRate);
uint32_t readFromUart(/*uint8_t *pMsg, uint32_t maxLen*/void);
uint32_t writeToUart(uint8_t *pMsg, uint32_t len);
void initGPIOD(void);
int isData(void);
void enableUsart3Interrupt(void(*function)(void));

#endif
