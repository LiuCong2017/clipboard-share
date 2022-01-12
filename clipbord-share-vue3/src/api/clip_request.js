import http from "@/utils/http";

export const getClipList = ()=>{
    return http.get('/clip/getAll')
}

//修改用
// export const getClipById = id=>{
//     return http.get('/clip/getClipById/'+id)
// }
export const updateClip = params =>{
    return http.put('/clip/updateClip',params)
}

export const delClipById = id =>{
    return http.delete('/clip/delete/'+id)
}

export const addClip = params=>{
    return http.post('/clip/save',params)
}
