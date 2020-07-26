#include <stdint.h>
#include <string.h>
#include <ctype.h>
#include "ourRccGpio.h"
#include "ourCom.h"
#include "sysClockAndTick.h"
#include "ourExti.h"

#define LED_VERDE 6
#define LED_NARANJA 7
#define LED_ROJO 8
#define USED_COM_PORT COM1
#define WKUP 0
#define TAMPER 13
#define N_LEDS 3
#define MINIMOA 0

void initGPIO(void);
void initGpioInterrupts(void);
void pertsonaSartu(void);
void jantokiEgoeraBegiratu(void);
void pertsonaAtera(void);
void usart3InterrupzioaAktibatu(void);
uint32_t aforo=14;
uint32_t pertsona=10;
uint32_t ledPins[N_LEDS]={6,7,8};
uint32_t actualLed=6;
uint8_t entrega[20];
int main(void)
{
  int i=0;
	
  initSysTick(500);
	initCom(USED_COM_PORT,9600);
  initGPIO();
	initGpioInterrupts();
	enableUsart3Interrupt(usart3InterrupzioaAktibatu);
  writeToUart((uint8_t *)"03usingUart\r\n",13);
  for(;;)
  {
		togleGpioPinValue(GPIOF, actualLed);
		if(isData()){
			entrega[i] = readFromUart();
			if(entrega[i] == '$'){
				writeToUart(entrega,i);
				entrega[i] = '0';
				i=0;
			}
			else i++;
		}
		waitSysTick();
	}
}

void initGPIO(void)
{
	initGPIOD();
  RCC_AHB1PeriphClockCmd(RCC_AHB1Periph_GPIOF, 1);
	RCC_AHB1PeriphClockCmd(RCC_AHB1Periph_GPIOA, 1);
	RCC_AHB1PeriphClockCmd(RCC_AHB1Periph_GPIOC, 1);
  for(int i=0;i<N_LEDS;i++) initGpioPinMode(GPIOF, ledPins[i], GPIO_Mode_OUT);
	initGpioPinMode(GPIOA, WKUP, GPIO_Mode_IN);
	initGpioPinMode(GPIOC, TAMPER, GPIO_Mode_IN);
}
void initGpioInterrupts(void)
{
	writeToUart((uint8_t*)"Paso\r\n",6);
  enablePA0interruptOnExti0WhenRising(pertsonaSartu);
  enablePC13interruptOnExti13WhenRising(pertsonaAtera);
}

void pertsonaSartu(void){
	pertsona++;
	jantokiEgoeraBegiratu();
}

void pertsonaAtera(void){
	pertsona--;
	jantokiEgoeraBegiratu();
}

void jantokiEgoeraBegiratu(void){
	setGpioPinValue(GPIOF,actualLed,0);
	if(pertsona>=(aforo*0.7) && pertsona<aforo){
		writeToUart((uint8_t*)"W\r\n",3);
		actualLed=LED_NARANJA;
	}
	else if(pertsona>=aforo){
		writeToUart((uint8_t*)"C\r\n",3);
		actualLed=LED_ROJO;
	}
	else if(pertsona <= MINIMOA){
		pertsona=0;
	}else{
		writeToUart((uint8_t*)"D\r\n",3);
		actualLed=LED_VERDE;
	}
}

void usart3InterrupzioaAktibatu(void){

uint8_t entrega[20];
	int i = 0;
	
	if(isData()){
  while(entrega[i-1] != '$'){
			
		  if(isData())
			{
			entrega[i] = readFromUart();
			i++;
		}	
	}
	writeToUart(entrega, i);
	}
}
