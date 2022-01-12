package com.clipboard.share.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.clipboard.share.api.dto.ClipDTO;
import com.clipboard.share.entity.Clip;
import com.clipboard.share.mapper.ClipMapper;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author : [Administrator]
 * @version : [v1.0]
 * @description : []
 * @createTime : [2021/10/27 8:31]
 */
@Api(tags = "clipboard module")
@RestController
@RequestMapping("/clip")
public class ClipController {

    @Autowired
    private ClipMapper clipMapper;

    @GetMapping("/getAll")
    public List<Clip> getAll(){
        return clipMapper.selectList(null);
    }

    @DeleteMapping("/delete/{id}")
    public int deleteClipById(@PathVariable(name = "id") int id){
        return clipMapper.deleteById(id);
    }

    @GetMapping("/getClipById/{id}")
    public Clip getClipById(@PathVariable(name = "id") int id) {
        return clipMapper.selectById(id);
    }
    @PutMapping("/updateClip")
    public int updateClip(@RequestBody ClipDTO clipDTO){
        QueryWrapper<Clip> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",clipDTO.getId());
        Clip clip = new Clip();
        clip.setItemContent(clipDTO.getItemContent());
        return clipMapper.update(clip,queryWrapper);
    }

    @PostMapping("/save")
    public int saveClip(@RequestBody ClipDTO clipDTO){
        Clip clip = new Clip();
        clip.setItemContent(clipDTO.getItemContent());

        return clipMapper.insert(clip);
    }


}
