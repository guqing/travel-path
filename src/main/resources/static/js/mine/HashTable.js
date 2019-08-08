/**
 * js HashTable哈希表实现
 * 参数及方法说明：
 * 函数名				|说明				|	返回值
 * ---------------------|-------------------|----------
 * add(key,value)		|添加项				|无
 * ----------------------------------------------------
 * getValue(key)		|根据key取值			|object
 * ----------------------------------------------------
 * remove(key)			|根据key删除一项		|无
 * ----------------------------------------------------
 * containsKey(key)		|是否包含某个key		|bool
 * ----------------------------------------------------
 * containsValue(value)	|是否包含某个值		|bool
 * ----------------------------------------------------
 * getValues()			|获取所有的值的数组	|array
 * ----------------------------------------------------
 * getKeys()			|获取所有的key的数组	|array
 * ----------------------------------------------------
 * getSize()			|获取项总数			|int
 * ----------------------------------------------------
 * clear()				|清空哈希表			|无
 */
function HashTable() {
    var size = 0;
    var entry = new Object();
    this.add = function (key, value) {
        if (!this.containsKey(key)) {
            size++;
        }
        entry[key] = value;
    }
    this.getValue = function (key) {
        return this.containsKey(key) ? entry[key] : null;
    }
    this.remove = function (key) {
        if (this.containsKey(key) && (delete entry[key])) {
            size--;
        }
    }
    this.containsKey = function (key) {
        return (key in entry);
    }
    this.containsValue = function (value) {
        for (var prop in entry) {
            if (entry[prop] == value) {
                return true;
            }
        }
        return false;
    }
    this.getValues = function () {
        var values = new Array();
        for (var prop in entry) {
            values.push(entry[prop]);
        }
        return values;
    }
    this.getKeys = function () {
        var keys = new Array();
        for (var prop in entry) {
            keys.push(prop);
        }
        return keys;
    }
    this.getSize = function () {
        return size;
    }
    this.clear = function () {
        size = 0;
        entry = new Object();
    }
}