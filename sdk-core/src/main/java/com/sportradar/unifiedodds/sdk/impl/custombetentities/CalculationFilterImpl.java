/*
 * Copyright (C) Sportradar AG. See LICENSE for full license governing this code
 */

package com.sportradar.unifiedodds.sdk.impl.custombetentities;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.sportradar.uf.custombet.datamodel.CAPICalculationResponse;
import com.sportradar.uf.custombet.datamodel.CAPIFilteredCalculationResponse;
import com.sportradar.unifiedodds.sdk.custombetentities.AvailableSelections;
import com.sportradar.unifiedodds.sdk.custombetentities.AvailableSelectionsFilter;
import com.sportradar.unifiedodds.sdk.custombetentities.Calculation;
import com.sportradar.unifiedodds.sdk.custombetentities.CalculationFilter;
import com.sportradar.utils.SdkHelper;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

/**
 * Implements methods used to provide a probability calculation
 */
public class CalculationFilterImpl implements CalculationFilter {

    private final double odds;
    private final double probability;
    private final List<AvailableSelectionsFilter> availableSelectionsList;
    private final Date generatedAt;

    public CalculationFilterImpl(CAPIFilteredCalculationResponse calculation) {
        Preconditions.checkNotNull(calculation);
        Preconditions.checkNotNull(calculation.getCalculation());

        this.odds = calculation.getCalculation().getOdds();
        this.probability = calculation.getCalculation().getProbability();
        this.availableSelectionsList = calculation.getAvailableSelections() != null
                ? calculation.getAvailableSelections().getEvents().stream().map(m->new AvailableSelectionsFilterImpl(m, calculation.getGeneratedAt())).collect(ImmutableList.toImmutableList())
                : ImmutableList.of();

        Date date = new Date();
        try {
            date = SdkHelper.toDate(calculation.getGeneratedAt());
        }
        catch (ParseException e) {
        }
        this.generatedAt = date;
    }

    /**
     * Gets the odds
     *
     * @return the odds
     */
    @Override
    public double getOdds() {
        return odds;
    }

    /**
     * Gets the probability
     *
     * @return the probability
     */
    @Override
    public double getProbability() {
        return probability;
    }

    /**
     * Returns list of available selections
     *
     * @return list of available selections
     */
    @Override
    public List<AvailableSelectionsFilter> getAvailableSelections() { return availableSelectionsList; }

    /**
     * Returns the date when API response was generated
     *
     * @return the date when API response was generated
     */
    @Override
    public Date getGeneratedAt() { return generatedAt; }
}
