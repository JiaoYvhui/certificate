package com.manage.certificate.controller;

import com.github.pagehelper.PageInfo;
import com.manage.certificate.comm.controller.BaseController;
import com.manage.certificate.comm.exception.BizException;
import com.manage.certificate.comm.exception.BizExceptionEnum;
import com.manage.certificate.comm.exception.BizResponseEnum;
import com.manage.certificate.comm.model.Response;
import com.manage.certificate.entity.DictType;
import com.manage.certificate.entity.DictValue;
import com.manage.certificate.service.DictTypeService;
import org.apache.commons.compress.utils.Lists;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
public class DictTypeController extends BaseController {

    @Resource
    private DictTypeService dictTypeService;

    @GetMapping(value = "/a/label", produces = "text/html;charset=utf-8")
    public String getDictLabel(String value, String type, String defaultLabel) {
        if (StringUtils.isNotBlank(type) && StringUtils.isNotBlank(value)) {
            for (DictValue dictValue : getDictList(type)) {
                if (value.equals(dictValue.getValue())) {
                    return dictValue.getLabel();
                }
            }
        }
        return defaultLabel;
    }

    @GetMapping(value = "/a/labels", produces = "text/html;charset=utf-8")
    public String getDictLabels(String values, String type, String defaultValue) {
        if (StringUtils.isNotBlank(type) && StringUtils.isNotBlank(values)) {
            List<String> valueList = Lists.newArrayList();
            for (String value : StringUtils.split(values, ",")) {
                valueList.add(getDictLabel(value, type, defaultValue));
            }
            return StringUtils.join(valueList, ",");
        }
        return defaultValue;
    }

    @GetMapping(value = "/a/value", produces = "text/html;charset=utf-8")
    public String getDictValue(String label, String type, String defaultLabel) {
        if (StringUtils.isNotBlank(type) && StringUtils.isNotBlank(label)) {
            for (DictValue dictValue : getDictList(type)) {
                if (label.equals(dictValue.getLabel())) {
                    return dictValue.getValue();
                }
            }
        }
        return defaultLabel;
    }

    @GetMapping(value = "/a/values", produces = "text/html;charset=utf-8")
    public String getDictValues(String label, String type, String defaultLabel) {
        if (StringUtils.isNotBlank(type) && StringUtils.isNotBlank(label)) {
            for (DictValue dictValue : getDictList(type)) {
                if (dictValue.getLabel().contains(label)) {
                    return dictValue.getValue();
                }
            }
        }
        return defaultLabel;
    }

    @GetMapping(value = "/a/dict")
    public List<DictValue> getDictList(String type) {
        DictType dictType = dictTypeService.getType(type);
        List<DictValue> dictList = dictType.getDictValueList();
        if (dictList == null) {
            dictList = new ArrayList<>();
        }
        return dictList;
    }

    @PostMapping("/a/dict/type/add")
    public Response addType(@RequestBody DictType dictType) {
        if (dictType != null) {
            dictType.setId(null);
            dictTypeService.save(dictType);
            return super.successResponse(BizResponseEnum.SUCCEED_INSERT);
        }
        return super.failResponse(BizExceptionEnum.VALUE_ERROR);
    }

    @DeleteMapping("/a/dict/type/del")
    public Response deleteType(String id) {
        dictTypeService.delete(id);
        return super.successResponse(BizResponseEnum.SUCCEED_DELETE);
    }

    @PutMapping("/a/dict/type/update")
    public Response updateType(@RequestBody DictType dictType) {
        if (dictType != null && dictType.getId() != null) {
            dictTypeService.save(dictType);
            return super.successResponse(BizResponseEnum.SUCCEED_UPDATE);
        } else if (dictType != null) {
            throw new BizException("错误，id为空，请确认值是否传递正确");
        }
        return super.failResponse(BizExceptionEnum.VALUE_ERROR);
    }

    @PostMapping("/a/dict/type")
    public Response findListType(@RequestBody DictType dictType) {
        PageInfo<DictType> list = dictTypeService.findPage(dictType);
        return super.successResponse(BizResponseEnum.SUCCEED_QUERY, list);
    }

    @PostMapping("/a/dict/value/add")
    public Response addValue(@RequestBody DictValue dictValue) {
        if (dictValue != null) {
            dictValue.setId(null);
            dictTypeService.saveDictValue(dictValue);
            return super.successResponse(BizResponseEnum.SUCCEED_INSERT);
        }
        return super.failResponse(BizExceptionEnum.VALUE_ERROR);
    }

    @DeleteMapping("/a/dict/value/del")
    public Response deleteValue(String id) {
        dictTypeService.deleteDictValue(id);
        return super.successResponse(BizResponseEnum.SUCCEED_DELETE);
    }

    @PutMapping("/a/dict/value/update")
    public Response updateValue(@RequestBody DictValue dictValue) {
        if (dictValue != null && dictValue.getId() != null) {
            dictTypeService.saveDictValue(dictValue);
            return super.successResponse(BizResponseEnum.SUCCEED_UPDATE);
        } else if (dictValue != null) {
            throw new BizException("错误，id为空，请确认值是否传递正确");
        }
        return super.failResponse(BizExceptionEnum.VALUE_ERROR);
    }

    @PostMapping("/a/dict/value")
    public Response findListValue(@RequestBody DictValue dictValue) {
        PageInfo<DictValue> list = dictTypeService.findPage(dictValue);
        return super.successResponse(BizResponseEnum.SUCCEED_QUERY, list);
    }
}
