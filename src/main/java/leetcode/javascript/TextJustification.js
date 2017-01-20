/**
 * @param {string[]} words
 * @param {number} maxWidth
 * @return {string[]}
 */
//https://leetcode.com/problems/text-justification/
var fullJustify = function(words, maxWidth) {
    if (words.every(item =>{return item.length===0;})){
        return [" ".repeat(maxWidth)];
    }

    let length=words.length;
    let count=0;
    let result = [];
    while (count<length){
        let currentLength=0;
        let tempResult = [];

        tempResult.push(words[count]);
        currentLength = currentLength + words[count].length;
        count++;

        while (count<length && currentLength + 1 + words[count].length<= maxWidth   ) {
            tempResult.push(" " + words[count]);
            currentLength = currentLength + words[count].length + 1;
            count++;
        }


        let diff = maxWidth - currentLength;

        if (tempResult.length!==1 && count!==words.length){

            let extraSpacePerWord = Math.floor(diff/(tempResult.length-1));
            let extraSpaceUneven = diff%(tempResult.length-1);
            for (let i = 1; i < tempResult.length; i++) {
                tempResult[i]= " ".repeat(extraSpacePerWord) + tempResult[i];
                if (i<=extraSpaceUneven){
                    tempResult[i]= " " + tempResult[i];
                }
            }
        } else {
            tempResult[tempResult.length-1]= tempResult[tempResult.length-1] + " ".repeat(diff);
        }

        let tempResultString =  tempResult.reduce((acc, item)=>{
                return acc+=item;
    });
        result.push(tempResultString);
    }
    return result;
};