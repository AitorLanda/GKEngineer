#include "ourExti.h"
#include <stm32f407xx.h>

#define SYSCFG_HASIERA 0x40013800
#define EXTI_HASIERA 0x40013C00
#define NVIC_HASIERA 0xE000E100
#define RCC_HASIERA 0x40023800

#define EXTICR1_OFFSET 0x08
#define EXTICR4_OFFSET 0x14
#define EXTI_IMR_OFFSET 0x00
#define EXTI_RTSR_OFFSET 0x08
#define EXTI_FTSR_OFFSET 0x0C
#define EXTI_PR_OFFSET 0x14

#define APB2ENR_OFFSET 0x44
#define SYSCFG_EN_BIT (0x01<<14)

CALLBACK exti0callback=0;
CALLBACK exti13callback=0;

void enablePA0interruptOnExti0WhenRising(CALLBACK pFun)
{
  uint32_t *p;
  
  p=(uint32_t*)(RCC_HASIERA+APB2ENR_OFFSET);
  *p|=SYSCFG_EN_BIT;
  p=(uint32_t*)(EXTI_HASIERA+EXTI_RTSR_OFFSET);
  *p|=0x01;
  p=(uint32_t*)(EXTI_HASIERA+EXTI_FTSR_OFFSET);
  *p&=~0x01;
  p=(uint32_t*)(EXTI_HASIERA+EXTI_IMR_OFFSET);
  *p|=0x01;
  p=(uint32_t*)(NVIC_HASIERA+0);
  *p|=0x01<<6;
  
  exti0callback=pFun;
}

void enablePC13interruptOnExti13WhenRising(CALLBACK pFun)
{
  uint32_t *p;
  
    //SYSCFG gahitu
  p=(uint32_t*)(RCC_HASIERA+APB2ENR_OFFSET);
  *p|=SYSCFG_EN_BIT;
  //sysConfig configuratu 
  p=(uint32_t*)(SYSCFG_HASIERA+EXTICR4_OFFSET);
  *p &=~(0x0F<<4);
  *p |= (0x02<<4);
  
  //exti konfiguratu
  p=(uint32_t*)(EXTI_HASIERA+EXTI_RTSR_OFFSET);
  *p|=0x01<<13;
  p=(uint32_t*)(EXTI_HASIERA+EXTI_FTSR_OFFSET);
  *p&=~(0x01<<13);
  p=(uint32_t*)(EXTI_HASIERA+EXTI_IMR_OFFSET);
  *p|=(0x01<<13);
  //NVIC konfiguratu
  p=(uint32_t*)(NVIC_HASIERA+0);
  *(p+40/32)|=0x01<<(40%32);
  
  exti13callback=pFun;
}

void ourExti0Handler(void)
{
  uint32_t *p;

  //PR-rn flag-a reseatu
  p=(uint32_t*)(EXTI_HASIERA+EXTI_PR_OFFSET);
  *p|=0x01;
  
  if(exti0callback!=0)
    exti0callback();
}

void ourExti10_15Handler(void)
{
  uint32_t *p, i, reg;

  p=(uint32_t*)(EXTI_HASIERA+EXTI_PR_OFFSET);
  reg=*p;
  for(i=10;i<=15;i++)
  {
    if(reg & (0x01<<i))
    {
      *p |= 0x01<<i;
      if((i==13) && (exti13callback!=0)) exti13callback();
    }
  }
}

















