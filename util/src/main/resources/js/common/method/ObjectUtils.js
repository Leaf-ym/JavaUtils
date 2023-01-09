/**
 * 根据键删除对象属性
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
function deleteByKey(object, key) {
    delete object[key];
    return object;
}

let object = {
    "key1": 1,
    "key2": 2
};
console.log(object);
console.log(deleteByKey(object, "key2"));