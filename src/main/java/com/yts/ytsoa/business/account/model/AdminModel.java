package com.yts.ytsoa.business.account.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

/**
 * @author LD
 */
@Slf4j
@JsonIgnoreProperties(ignoreUnknown = true)
public class AdminModel extends AccountModel implements Serializable {


}
