// 随机串
function restoreString(s: string, indices: number[]): string {
    let arr: Word[] = []
    for (let i = 0; i < indices.length; i++) {
        const word = new Word();
        word.name = s.charAt(i);
        word.idx = indices[i]
        arr.push(word)
    }
    return arr.sort((a, b) => a.idx - b.idx).map(item => item.name).join("")
}

class Word {
    name: string;
    idx: number
}   
