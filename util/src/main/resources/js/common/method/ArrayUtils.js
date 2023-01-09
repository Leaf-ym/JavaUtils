/**
 * 根据索引删除数组元素
 *
 * @param arr
 *
 * @param index
 *
 * @author wengym
 *
 * @date 2022/8/11 15:39
 *
 * @return
 */
function deleteByIndex(arr, index) {
    if (Array.isArray(arr) && (arr.length -1) >= index) {
        arr.splice(index, 1);
    }
    return arr;
}

let arr = ["a", "b", "c", "d"];
console.log(arr);
console.log(deleteByIndex(arr, 0));