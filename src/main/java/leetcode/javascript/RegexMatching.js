/**
 * @param {string} s
 * @param {string} p
 * @return {boolean}
 */

//https://leetcode.com/problems/regular-expression-matching/?tab=Description
var isMatch = function(s, p) {
    if (s==="" && p===""){
        return true;
    }

    let regexCharacters = ["",...p];
    let stringCharacters = ["",...s];
    let matchResultArray = [[]];

    for (let characterIndex=0; characterIndex<stringCharacters.length;characterIndex++ ){
        matchResultArray[characterIndex] = new Array(regexCharacters.length);
        matchResultArray[characterIndex][0] = false;
    }
    matchResultArray[0][0]=true;
    matchResultArray[0][1]=regexCharacters[1]=="*"?true:false;

    for (let regexCharacterIndex=2; regexCharacterIndex<regexCharacters.length;regexCharacterIndex++ ){
        if ((matchResultArray[0][regexCharacterIndex-1]|| matchResultArray[0][regexCharacterIndex-2]) && regexCharacters[regexCharacterIndex]==="*" ){
            matchResultArray[0][regexCharacterIndex]=true;
        } else {
            matchResultArray[0][regexCharacterIndex]=false;
        }
    }

    for (let characterIndex=1; characterIndex<stringCharacters.length; characterIndex++ ){
        for (let regexCharacterIndex=1; regexCharacterIndex<regexCharacters.length; regexCharacterIndex++ ){
            if ( regexCharacters[regexCharacterIndex]==="*" &&
                (
                    //* as 0 or 1 occurances of matching chsaracter
                    matchResultArray[characterIndex][regexCharacterIndex-2] ||
                    matchResultArray[characterIndex][regexCharacterIndex-1] ||

                    //repeating a using a*
                    (
                        matchResultArray[characterIndex-1][regexCharacterIndex-1] &&
                        stringCharacters[characterIndex]===stringCharacters[characterIndex-1]
                    )  ||

                    //repeating using .*
                    (regexCharacters[regexCharacterIndex-1]==="." && matchResultArray[characterIndex-1][regexCharacterIndex])
                )
            ){
                matchResultArray[characterIndex][regexCharacterIndex]=true;
            } else if ( matchResultArray[characterIndex-1][regexCharacterIndex-1] && (regexCharacters[regexCharacterIndex]==="." || stringCharacters[characterIndex] === regexCharacters[regexCharacterIndex]) ){
                matchResultArray[characterIndex][regexCharacterIndex]=true;
            } else {
                matchResultArray[characterIndex][regexCharacterIndex]=false;
            }
        }
    }
    return matchResultArray[stringCharacters.length-1][regexCharacters.length-1];
};