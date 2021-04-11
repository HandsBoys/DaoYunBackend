package com.dy.service;

import com.dy.domain.SysDictData;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 */
public interface SysDictDataService {

   public List<SysDictData> listDictData(SysDictData dictData);

   public int insertDictData(SysDictData dictData);

   public int updateDictData(SysDictData dictData);

   public boolean checkDictTypeStatus(SysDictData dictData);

   public int deleteDictDataById(SysDictData dictData);
}
