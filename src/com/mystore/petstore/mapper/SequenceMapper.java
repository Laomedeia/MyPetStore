package com.mystore.petstore.mapper;

import com.mystore.petstore.entity.Sequence;


/**
 * 序列相关crud接口类
 * @author siming
 *
 */
public interface SequenceMapper {

  Sequence getSequence(Sequence sequence);
  void updateSequence(Sequence sequence);
}
