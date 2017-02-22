/**
 * @param {string[]} tokens
 * @return {number}
 */
//https://leetcode.com/problems/evaluate-reverse-polish-notation/?tab=Description
var evalRPN = function (tokens) {
    if (tokens.length === 1) {
        return Number.parseInt(tokens[0]);
    }
    let stack = [];
    let operand1;
    let operand2;
    let result;
    let token;
    for (token of tokens) {
        switch (token) {
            case "+":
                operand1 = stack.pop();
                operand2 = stack.pop();
                console.log(operand1);
                console.log(operand2);
                result = operand2 + operand1;
                console.log(result);
                stack.push(Number.parseInt(result));
                break;
            case "-":
                operand1 = stack.pop();
                operand2 = stack.pop();
                result = operand2 - operand1;
                stack.push(Number.parseInt(result));
                break;
            case "*":
                operand1 = stack.pop();
                operand2 = stack.pop();
                result = operand2 * operand1;
                stack.push(Number.parseInt(result));
                break;
            case "/":
                operand1 = stack.pop();
                console.log(typeof(operand1));
                operand2 = stack.pop();
                result = operand2 / operand1;
                console.log(result);
                stack.push(Number.parseInt(result));
                break;
            default:
                console.log(token);
                stack.push(Number.parseInt(token));
        }
    }
    return stack.pop();
};