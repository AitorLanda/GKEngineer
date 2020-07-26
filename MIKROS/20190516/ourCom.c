#include "ourCom.h"

void (*callbackUSART3)()=0;

uint32_t initCom(COM com, uint32_t baudRate){
		
	RCC->APB1ENR |= RCC_APB1ENR_USART3EN;
	
	USART3->BRR= getSysClockFrequency()/baudRate;
		
	USART3->CR1 &= ~USART_CR1_RE;
	USART3->CR1 |= USART_CR1_RE;
	USART3->CR1 &= ~USART_CR1_TE;
	USART3->CR1 |= USART_CR1_TE;
	USART3->CR1 &= ~USART_CR1_UE;
	USART3->CR1 |= USART_CR1_UE;
	return 1;
}

uint32_t readFromUart(){
	uint8_t dato;
	dato = USART3->DR; 
	USART3->SR &= ~USART_SR_RXNE;
	return dato;
}

uint32_t writeToUart(uint8_t *pMsg, uint32_t len){
	for(int i = 0; i<len; i++){
		while(!(USART3->SR & USART_SR_TXE)){};
			USART3->DR = *(pMsg+i);
	}
	return *pMsg;
}

void initGPIOD(void){
	
	RCC->AHB1ENR |= RCC_AHB1ENR_GPIODEN;
	GPIOD->MODER |= GPIO_MODER_MODE8_1;
	GPIOD->MODER |= GPIO_MODER_MODE9_1;
	GPIOD->AFR[1] |= (0x07 << GPIO_AFRH_AFSEL8_Pos);
	GPIOD->AFR[1] |= (0x07 << GPIO_AFRH_AFSEL9_Pos);
}


void enableUsart3Interrupt(void(*function)(void)){
	USART3->CR1 &= ~USART_CR1_RXNEIE;
	USART3->CR1 |= USART_CR1_RXNEIE;
	NVIC->ISER[USART3_IRQn/32] = 0x01 << (USART3_IRQn%32);
	if(function) callbackUSART3 = function;
}

void ourUsart3Handler(){
		callbackUSART3();
}

int isData(void){
		if(USART3->SR & USART_SR_RXNE)
			return 1;
		else
			return 0;
}
