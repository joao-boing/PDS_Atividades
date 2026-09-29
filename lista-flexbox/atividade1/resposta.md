# Atividade 1 - Flexbox Froggy

Joguei todos os níveis do Flexbox Froggy (https://flexboxfroggy.com/#pt-br).
Abaixo está a explicação de 2 níveis acima do 10.

## Nível 13

Solução:

```css
#pond {
  display: flex;
  flex-direction: row-reverse;
  justify-content: center;
  align-items: flex-end;
}
```

- `display: flex;` transforma o lago (#pond) em um container flex, assim os sapos (itens) podem ser organizados com as propriedades do flexbox.
- `flex-direction: row-reverse;` deixa os itens em linha, mas na ordem invertida, ou seja, o primeiro sapo vai para a direita e o último para a esquerda. Isso foi preciso porque as cores dos sapos estavam na ordem contrária das folhas.
- `justify-content: center;` alinha os itens no centro do eixo principal (que aqui é horizontal), então os sapos ficam no meio do lago na horizontal.
- `align-items: flex-end;` alinha os itens no final do eixo transversal (vertical), então os sapos descem para a parte de baixo do lago, onde estavam as folhas.

## Nível 17

Solução:

```css
.yellow {
  order: 1;
  align-self: flex-end;
}
```

- `order: 1;` muda a ordem de um item só. Todos os itens começam com order 0, então colocando 1 no sapo amarelo ele vai para o final da fila, depois dos outros.
- `align-self: flex-end;` funciona igual ao align-items, mas só para um item. Com ele o sapo amarelo foi para baixo, enquanto os outros continuaram em cima.

## Referências

FLEXBOX FROGGY. Flexbox Froggy: um jogo para aprender CSS flexbox. Disponível em: https://flexboxfroggy.com/#pt-br.

MDN WEB DOCS. Conceitos básicos de flexbox. Disponível em: https://developer.mozilla.org/pt-BR/docs/Web/CSS/CSS_Flexible_Box_Layout/Basic_Concepts_of_Flexbox.
