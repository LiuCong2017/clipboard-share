<template>
  <div class="handle-box" style="margin-left: 0">
    <el-button type="primary" icon="el-icon-plus" @click="beforeAdd">新增</el-button>
  </div>
  <div class="app-container">

    <el-table :data="clips" border style="width: 100%">
      <el-table-column type="index" width="50" />
      <el-table-column prop="itemContent" label="剪切内容" align="center" />
      <el-table-column prop="createTime" label="创建时间" width="150" align="center" />
      <el-table-column label="操作" fixed="right" width="100" align="center">
        <template v-slot:default="scope">
          <el-button
              type="text"
              icon="el-icon-delete"
              style="color: red"
              @click="handleDelete(scope.$index, scope.row)"
          ></el-button>
          <el-button type="text" icon="el-icon-edit" size="small" @click="modify"/>
        </template>

      </el-table-column>
    </el-table>


    <!-- 新增 -->
    <el-dialog v-model="addVisible" title="新增">
      <el-form :model="addClipItems">
        <el-form-item label="剪贴板内容" :label-width="formLabelWidth">
          <el-input v-model="addClipItems.itemContent" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <template #footer>
      <span class="dialog-footer">
        <el-button @click="addVisible = false">取消</el-button>
        <el-button type="primary" @click="addClipboard"
        >确认</el-button
        >
      </span>
      </template>
    </el-dialog>


  </div>
</template>

<script setup>

import {getClipList,addClip,delClipById} from "@/api/clip_request";
import {ElMessage} from "element-plus";
import {onBeforeUpdate, onMounted, reactive, ref} from "vue";

const formLabelWidth = ref('120px');
let addClipItems = reactive({
  content:''
});
let addVisible = ref(false);
let clips = reactive([]);


const getClipRecord = ()=>{

    getClipList().then(res=>{
      console.log(res)
      // if (clips !== []){
      //   clips = []
      // }
      res.data.forEach((item)=>{
        clips.push(item)
      });

    }).catch(err=>{
      ElMessage.error('获取数据失败')
    })

}
onMounted(getClipRecord);
onBeforeUpdate(getClipRecord)

//新增
const beforeAdd = ()=>{
  addClipItems.content = '';
  addVisible = true;
}
const addClipboard = ()=>{
  if (addClipItems.content === null || ''){
    ElMessage.warning('内容为空');
    return;
  }
  addClip({itemContent: addClipItems.content}).then(res=>{
    getClipRecord();
    ElMessage.success('提交成功');
    addVisible = false;
  }).catch(err=>{
    ElMessage.error('提交失败')
  }).finally(()=>{
    addVisible = false;
  })
}

//删除
const handleDelete = (index, row)=>{
  delClipById(row.id).then(res=>{
    getClipRecord();
    ElMessage.success("删除成功")
  }).catch(err=>{
    ElMessage.error('删除失败')
  })
}

//修改
const modify = ()=>{

}

</script>

<style scoped>
.app-container {
  padding: 20px;
}
.handle-box {
  margin-bottom: 20px;
}

@media screen and (max-width: 500px) {
  .el-message {
    min-width: 300px !important;
  }
}
@media screen and (max-width: 500px) {
  .el-dialog__wrapper .el-dialog {
    width: 300px !important;
  }
  .el-dialog__body{
    padding: 10px 20px!important;
  }
  .el-form-item__label{
    width: 68px!important;
  }
  .el-select,.el-input{
    width: 180px!important;
  }
}
</style>
