<template>
  <div class="handle-box" style="margin-left: 0">
    <el-button type="primary" icon="el-icon-plus" @click="beforeAdd">新增</el-button>
  </div>
  <div class="app-container">

    <el-table :data="clips" border style="width: 100%">
      <el-table-column type="index" width="50" />
      <el-table-column prop="itemContent" label="剪切内容" align="center" />
      <el-table-column prop="createTime" label="创建时间" width="180" align="center" />
      <el-table-column label="操作" fixed="right" width="100" align="center">
        <template v-slot:default="scope">
          <el-button type="text" icon="el-icon-delete" size="small" style="color: red" @click="handleDelete(scope.$index, scope.row)"/>
          <el-button type="text" icon="el-icon-edit" size="small" @click="handleEdit(scope.$index, scope.row)"/>
        </template>

      </el-table-column>
    </el-table>


    <!-- 新增 -->
    <el-dialog v-model="addVisible" title="新增">
      <el-form :model="form">
        <el-form-item label="剪贴板内容" :label-width="formLabelWidth">
          <el-input v-model="form.content" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <template #footer>
      <span class="dialog-footer">
        <el-button @click="addVisible = false">取消</el-button>
        <el-button type="primary" @click="addClipboard" :disabled="addDisable">确认</el-button>
      </span>
      </template>
    </el-dialog>

<!--    修改-->
    <el-dialog v-model="modifyVisible" title="修改">
      <el-form :model="updateForm">
        <el-form-item label="剪贴板内容" :label-width="formLabelWidth">
          <el-input v-model="updateForm.itemContent" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <template #footer>
        <span class="dialog-footer">
          <el-button @click="modifyVisible = false">取消</el-button>
        <el-button type="primary" @click="updateClipboard" :disabled="updateDisable">更新</el-button>
        </span>
      </template>
    </el-dialog>

  </div>
</template>

<script>
import {getClipList,addClip,delClipById,updateClip} from "@/api/clip_request";
import {ElMessage} from "element-plus";
import {computed, onMounted, reactive, toRefs, watch, watchEffect} from "vue";

export default {
  name: "index",
  components:{},
  setup(){
    const state = reactive({
      clips: [],

      form:{
        content:''
      },
      formLabelWidth: '120px',
      addVisible: false,
      addDisable: true,

      updateForm:{
        id:0,
        itemContent:'',
      },
      modifyVisible: false,
      updateDisable: true,

    });

    //获取
    const getClipRecord = ()=>{
      state.clips = [];
      getClipList().then(res=>{
        res.data.forEach((item)=>{
          item.createTime = dateFilter(item.createTime);
          state.clips.push(item)
        });
      }).catch(err=>{
        console.log(err)
        ElMessage.error('获取数据失败')
      })
    }
    const dateFilter = (date)=>{
      let time = new Date(date);
      let y = time.getFullYear();
      let M = time.getMonth()+1;
      let d = time.getDate();
      let h = time.getHours();
      let m = time.getMinutes();
      let s = time.getSeconds();
      return y+'-'+M+'-'+d+' '+h+':'+m+':'+s
    }

    //新增
    const beforeAdd = ()=>{
      state.form.content = '';
      state.addVisible = true;
    }
    const addClipboard = ()=>{
      console.log(state.form.content);
      addClip({itemContent: state.form.content}).then(res=>{
        // console.log(res)
        getClipRecord();
        ElMessage.success('新增成功');
        state.addVisible = false;
      }).catch(err=>{
        ElMessage.error('新增失败')
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
    const handleEdit = (index,row)=>{
      state.updateForm = Object.assign({},row);
      state.modifyVisible = true;
    }
    const updateClipboard = () => {
      updateClip(state.updateForm).then(res=>{
        if (res.data>=1){
          getClipRecord();
          ElMessage.success("修改成功")
          state.modifyVisible = false
        }
      }).catch(err=>{
        ElMessage.error('修改失败')
      })
    }

    onMounted(() => {
      getClipRecord();
    })

    let computedAttr = computed(()=>{
      return 0;
    })

    //监听值是否为空
    watch(state.form,(oldVal,newVal)=>{
      state.addDisable = state.form.content === '';
    },{deep:true})

    watchEffect(()=>{
      state.updateDisable = state.updateForm.itemContent === '';
    })

    return{
      ...toRefs(state),
      getClipRecord,
      dateFilter,
      beforeAdd,
      addClipboard,
      handleDelete,
      handleEdit,
      updateClipboard,
    }
  },
  computed(){

  },

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
