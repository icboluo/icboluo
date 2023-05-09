function findWords(words: string[]): string[] {
    let first = "qwertyuiop"
    let second = "asdfghjkl";
    let third = "zxcvbnm";
    let res: string[] = []
    for (let word of words) {
        let temp = []
        for (let i = 0; i < word.length; i++) {
            if (first.indexOf(word.charAt(i).toLowerCase()) != -1) {
                temp[i] = 1;
            } else if (second.indexOf(word.charAt(i).toLowerCase()) != -1) {
                temp[i] = 2;
            } else if (third.indexOf(word.charAt(i).toLowerCase()) != -1) {
                temp[i] = 3;
            }
        }
        // @ts-ignore
        const set = [...new Set(temp)];
        if (set.length == 1) {
            res.push(word)
        }
    }
    return res
}
// FIXME ERROR
